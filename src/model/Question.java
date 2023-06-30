package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question {

    //properties
    private int questionID;
    private String question;
    private String options;
    private int answer;

    private int genreId;

    //getter

    public int getQuestionID(){
        return questionID;
    }
    public String getQuestion(){
        return question;
    }
    public String getOptions(){
        return options;
    }
    public int getAnswer(){
        return answer;
    }

    public int getGenreId(){
        return genreId;
    }
    //setter


    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setGenreId(int genreId){
        this.genreId = genreId;
    }

    //constructions
    public Question(int questionID, String question, String options, int answer, int genreId){
        this.questionID = questionID;
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.genreId = genreId;
    }

    public Question(){

    }



}
