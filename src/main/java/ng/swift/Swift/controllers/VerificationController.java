package ng.swift.Swift.controllers;

import lombok.RequiredArgsConstructor;
import ng.swift.Swift.exception.ErrorResponse;
import ng.swift.Swift.models.ResourceConstant;
import ng.swift.Swift.repositories.UserRepository;
import ng.swift.Swift.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resource/verify")
public class VerificationController {
    private final UserRepository userRepository;

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<String> verifyResource(@RequestParam("identifier")
                                                         String identifier, @RequestParam("type")
                                                         ResourceConstant type) {
        if (type == ResourceConstant.EMAIL) {
            if (userRepository.findActiveByEmail(identifier).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } else if (type == ResourceConstant.PHONE_NUMBER) {
            if (userRepository.findActiveByPhoneNumber(Utils.formatPhoneNumber(identifier)).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new ErrorResponse(HttpStatus.BAD_REQUEST, "Not Supported");
        }
    }
}
