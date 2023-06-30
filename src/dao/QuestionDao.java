package dao;
import model.Question;


public interface QuestionDao {
    Question getQuestionByGenreId(int genreId);
    Question getRandomQuestionByGenreId(int genreId);

    Question getRandomQuestion();




}
