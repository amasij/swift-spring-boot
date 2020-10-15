package ng.swift.Swift.startup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import ng.swift.Swift.models.EntityStatusConstant;
import ng.swift.Swift.models.MealCategory;
import ng.swift.Swift.models.State;
import ng.swift.Swift.repositories.MealCategoryRepository;
import ng.swift.Swift.repositories.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Loader {
    private final StateRepository stateRepository;
    private final TransactionTemplate transactionTemplate;
    private final MealCategoryRepository mealCategoryRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Gson gson;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        transactionTemplate.execute(tx -> {
            try {
                loadData();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return null;
        });
    }

    private void loadData() throws IOException{
        if(stateRepository.count() == 0){
            loadStates();
        }
        if(mealCategoryRepository.count() == 0){
            loadMealCategories();
        }
    }

    private void loadStates() throws IOException {
        try (InputStreamReader reader = new InputStreamReader(new ClassPathResource("records/state.json").getInputStream())) {
            State[] dtoList = gson.fromJson(gson.newJsonReader(reader), State[].class);

            List<State> states = new ArrayList<>();
            for (State state : dtoList) {
                if(!stateRepository.findByCode(state.getCode()).isPresent()){
                    state.setId(null);
                    state.setStatus(EntityStatusConstant.ACTIVE);
                    states.add(state);

                }
            }
            stateRepository.saveAll(states);
        }
    }

    private void loadMealCategories() throws IOException {
        try (InputStreamReader reader = new InputStreamReader(new ClassPathResource("records/meal_categories.json").getInputStream())) {
            MealCategory[] dtoList = gson.fromJson(gson.newJsonReader(reader), MealCategory[].class);

            List<MealCategory> mealCategories = new ArrayList<>();
            for (MealCategory mealCategory : dtoList) {
                if(!mealCategoryRepository.findActiveByName(mealCategory.getName().toLowerCase().trim()).isPresent()){
                    mealCategory.setId(null);
                    mealCategory.setStatus(EntityStatusConstant.ACTIVE);
                    mealCategories.add(mealCategory);

                }
            }
            mealCategoryRepository.saveAll(mealCategories);
        }
    }
}
