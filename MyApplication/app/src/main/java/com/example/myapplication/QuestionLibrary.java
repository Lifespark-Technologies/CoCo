package com.example.myapplication;

public class QuestionLibrary {

    private String mQuestions [] = {
            "How old are you",
            "Select that applies",
            "Have you recently started experiencing any of these symptoms?",
            "Have you recently started experiencing any of these symptoms?",
            "In the last 14 days, have you been in an area where COVID‑19 is widespread?",
            "In the last 14 days, what is your exposure to others who are known to have COVID‑19?",
            "Do you work in a medical facility?\n" +
                    "This includes a hospital, emergency room, other medical setting or long-term care facility."
    };


    private String mChoices [][] = {
            {"Below 18", "18-40", "40-64","65 or Above"},

            {"FrSevere, constant chest pain or pressure\n" +
                    "Extreme difficulty breathing ", "Severe, constant lightheadedness\n" +
                    "Serious disorientation or unresponsiveness", "None Of the above","All the Above"},

            {"Fever or Chills\n" +
                    "Mild or moderate difficulty breathing\n" +
                    "New or Worsening cough", "Sore throat \n" +
                    "Vomitting or diarrhoea\n" +
                    "Aching throughout body", "all the above","None of the above"},

            {"I live in an area where COVID-19 is widespread", "I have visited an area where COVID-19 is widespread", "Both","None of the above"},

            {"I live in with someone who has COVID-19","I have been in close contact with someone who has COVID-19"
                    ,"I have no exposure","I do not know"},

            {"I have worked in a hospital or other facility in\n" +
                    "the past 14 days","I plan to work in a hospital or other facility in\n" +
                    "the next 14 days","I do not work in a medical facility","I do not plan to work in a medical facility"}
    };



    private String mCorrectAnswers[] = {"", "", "", ""};




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getChoice4(int a){
        String choice3 = mChoices[a][3];
        return choice3;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}
