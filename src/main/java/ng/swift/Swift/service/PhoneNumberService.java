package ng.swift.Swift.service;

public interface PhoneNumberService {
    String formatPhoneNumber(String phoneNumber);

    boolean isValid(String value);
}
