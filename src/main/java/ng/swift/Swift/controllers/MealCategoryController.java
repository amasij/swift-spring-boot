package ng.swift.Swift.controllers;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.models.Meal;
import ng.swift.Swift.models.MealCategory;
import ng.swift.Swift.repositories.MealCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals-categories")
public class MealCategoryController {
    private final MealCategoryRepository mealCategoryRepository;

    @GetMapping("/search")
    public ResponseEntity<List<MealCategory>> getMeal(){
        return ResponseEntity.status(HttpStatus.OK).body(mealCategoryRepository.findAllActive());
    }



}
