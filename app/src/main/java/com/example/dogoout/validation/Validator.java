package com.example.dogoout.validation;

import android.util.Log;

import com.example.dogoout.domain.dog.Dog;
import com.example.dogoout.domain.user.User;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Validator {

    //Full user validation (user)
    public static boolean UserValidation (User user) {
        //Check if the user is valid
        boolean dogValid = true;
        boolean userValid = (isValidName(user.getFirstname()) &&
                isValidName(user.getSurname()) &&
                isValidEmail(user.getEmail()) &&
                isValidTextLength(user.getCountry()) &&
                isValidAge(user.getBirthDate()) &&
                isValidTextLength(user.getGender()) &&
                isValidTextLength(user.getDescription()) &&
                isValidTextLength(user.getPrompt()) &&
                isValidPhoto(user.getPhotosUser()) &&
                isValidTextLength(user.getPromptAnswer()) &&
                isValidAge(user.getPreference().getMinAge(), user.getPreference().getMaxAge()) &&
                isValidTextLength(user.getPreference().getSexPreference()) &&
                isValidTextLength(user.getPreference().getDogOwnerPreference()) &&
                isValidTextLength(user.getPreference().getDogBreedPreference()) &&
                isValidPhoto(user.getPhotosUser()));

        if (user.getDogs() != null && user.getDogs().size() > 0) {
            Iterator<Dog> iterator = user.getDogs().iterator();
            while (iterator.hasNext() && dogValid) {
                Dog dog = iterator.next();
                if (!DogValidation(dog)) dogValid = false;
            }
        }

        //Check if the user and the dog(s) are valid
        boolean toReturn = (userValid && dogValid);
        //Log if the user is valid or not
        Log.d("VALIDATION", "UserValidation: " + toReturn + " ( with userValid: " + userValid + " and dogValid: " + dogValid + " )");

        return toReturn;
    }

    //Full dog validation (dog)
    public static boolean DogValidation (Dog dog) {
        //Check if the dog is valid
        boolean toReturn = (isValidName(dog.getName()) &&
                isValidName(dog.getBreed()) &&
                isValidTextLength(dog.getPrompt()) &&
                isValidTextLength(dog.getPromptAnswer()) &&
                isValidCharacteristics(dog.getCharacteristics(), 0, 10) &&
                isValidPhoto(dog.getPhotosDog()));

        //Log if the dog is valid or not
        Log.d("VALIDATION", "DogValidation: " + toReturn);

        return toReturn;
    }



    //Email validation (user email)
    public static boolean isValidEmail(String email) {
        //Check if it is a valid email address
        if (email == null) return false;
        boolean toReturn = (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        //Log if the email is valid or not
        Log.d("VALIDATION", "isValidEmail: " + toReturn);

        return toReturn;
    }
    //Password validation (user password)
    public static boolean isValidPassword(String password) {
        //Check if it is at least 6 characters long, has at least one uppercase letter, one lowercase letter and one number
        if (password == null) return false;

        boolean toReturn = (password.length() >= 6 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*"));

        //Log if the password is valid or not
        Log.d("VALIDATION", "isValidPassword: " + toReturn);

        return toReturn;
    }

    //Name and Surname validation (user name and dog name)
    public static boolean isValidName(String name) {
        //Check if it is at least 2 characters long and doesn't contain any numbers
        if (name == null) return false;

        boolean toReturn = (name.length() >= 2 &&
                !name.matches(".*\\d.*"));

        //Log if the name is valid or not
        Log.d("VALIDATION", "isValidName: " + toReturn);

        return toReturn;
    }

    //Photo validation (user photo and dog photo)
    public static boolean isValidPhoto(ArrayList<URI> photos) {
        //Check if it is at least 1 photo and its the right format
        if (photos == null) return false;
        boolean toReturn =  true;
        if (photos.size() > 0) {
            Iterator<URI> iterator = photos.iterator();
            while (iterator.hasNext() && toReturn) {
                URI photo = iterator.next();
                if (!photo.toString().matches(".*\\.(jpg|jpeg|png|gif|bmp|webp)"))
                    toReturn = false;
            }
        }
        else toReturn = false;

        //Log if the photo is valid or not
        Log.d("VALIDATION", "isValidPhoto: " + toReturn);

        return toReturn;
    }

    //Any type of text validation (for long texts like description, prompt, promptAnswer, etc. and multiple choice answers)
    //min and max are the minimum and maximum length of the text
    public static boolean isValidTextLength(String text, int min, int max) {
        //Check if it is at least min characters long and at most max characters long
        if (text == null) return false;

        boolean toReturn = (text.length() >= min &&
                text.length() <= max);

        //Log if the text is valid or not
        Log.d("VALIDATION", "isValidTextLength: " + toReturn + " ( with min: " + min + "  and max: " + max + " )");
        return toReturn;
    }
    //Default min (0) and max (10000) values for text validation
    public static boolean isValidTextLength(String text) {
        //Check if it is at least min characters long and at most max characters long
        if (text == null) return false;

        boolean toReturn = (text.length() > 0 &&
                text.length() <= 10000);

        //Log if the text is valid or not
        Log.d("VALIDATION", "isValidTextLength: " + toReturn + " ( with min: " + 0 + "  and max: " + 10000 + " )");
        return toReturn;
    }

    //Age validation (user preference age)
    public static boolean isValidAge(int ageMin, int ageMax) {
        //Check if it is at least 18 years old and at most 100 years old
        boolean toReturn = (ageMin >= 18 && ageMax <= 100 &&
                ageMin <= ageMax);

        //Log if the age is valid or not
        Log.d("VALIDATION", "isValidAge: " + toReturn + " ( with min: " + ageMin + "  and max: " + ageMax + " )");
        return toReturn;
    }
    //Default min (18) and max (100) values for age validation
    public static boolean isValidAge(int age) {
        //Check if it is at least 18 years old and at most 100 years old
        boolean toReturn = (age >= 18 && age <= 100);

        //Log if the age is valid or not
        Log.d("VALIDATION", "isValidAge: " + toReturn + " ( with min: " + 18 + "  and max: " + 100 + " )");
        return toReturn;
    }
    public static boolean isValidAge(LocalDate age){
        int day = age.getDayOfMonth();
        int month = age.getMonthValue();
        int year = age.getYear();
        LocalDate today = LocalDate.now();
        //Check if it is at least 18 years old and at most 100 years old
        boolean toReturn = (year <= today.getYear() - 18 &&
                year >= today.getYear() - 100);

        //Log if the age is valid or not
        Log.d("VALIDATION", "isValidAge: " + toReturn + " ( with min: " + (today.getYear() - 100) + "  and max: " + (today.getYear() - 18) + " )");
        return toReturn;
    }

    //Characteristics validation (dog characteristics)
    public static boolean isValidCharacteristics(ArrayList<String> characteristics, int min, int max) {
        //Check if it is at least 1 characteristic and at most 10 characteristics
        if (characteristics == null) return false;

        boolean toReturn = (characteristics.size() > min &&
                characteristics.size() <= max);

        //Log if the characteristics are valid or not
        Log.d("VALIDATION", "isValidCharacteristics: " + toReturn + " ( with min: " + min + "  and max: " + max + " )");
        return toReturn;
    }
}
