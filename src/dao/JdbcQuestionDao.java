package dao;
import exception.DaoException;
import model.Question;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;


public class JdbcQuestionDao implements QuestionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcQuestionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Question getQuestionByGenreId(int genreId){
        Question question = null;
        String sql = "SELECT question_id, question, options, answer, genre_id "+
                "FROM question "+
                "WHERE genre_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreId);
            if(results.next()){
                question = mapRowToQuestion(results);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw  new DaoException("Unable to connect to server or database", e);
        }
        return question;
    }

    @Override
    public Question getRandomQuestionByGenreId(int genreId) {
        Question question = null;
        String sql = "SELECT question_id, question, options, answer, genre_id FROM question WHERE genre_id = ? ORDER BY RANDOM() LIMIT 1;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreId);
            if (results.next()) {
                question = mapRowToQuestion(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return question;
    }
    @Override
    public Question getRandomQuestion() {
        Question question = null;
        String sql = "SELECT * FROM question ORDER BY RANDOM() LIMIT 1;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                question = mapRowToQuestion(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
       return question;
    }

    private Question mapRowToQuestion(SqlRowSet rowSet) {
        Question question = new Question();
        question.setQuestionID(rowSet.getInt("question_id"));
        question.setQuestion(rowSet.getString("question"));
        question.setOptions(rowSet.getString("options"));
        question.setAnswer(rowSet.getInt("answer"));
        question.setGenreId(rowSet.getInt("genre_id"));
        return question;
    }
}
