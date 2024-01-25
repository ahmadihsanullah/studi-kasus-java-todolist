package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.protocol.Resultset;

import entity.Todolist;

public class TodolistRepositoryImpl implements TodolistRepository{

    public DataSource dataSource;

    public TodolistRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Todolist[] getAll() {
        String sql = "SELECT id, todo FROM todolist";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
                List<Todolist> list = new ArrayList<>();
                while(resultSet.next()){
                    Todolist todolist = new Todolist();
                    todolist.setId(resultSet.getInt("id"));
                    todolist.setTodo(resultSet.getString("todo"));

                    list.add(todolist);
                }

                return list.toArray(new Todolist[]{});
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 
    @Override
    public void add(Todolist todolist) {
        String sql = "INSERT INTO todolist(todo) VALUES (?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            
                preparedStatement.setString(1, todolist.getTodo());
                preparedStatement.executeUpdate();

        }catch(SQLException exception){
            throw new RuntimeException(exception);
        }

    }

    private boolean isExist(Integer number){
        String sql = "SELECT * FROM todolist WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, number);  

            try(ResultSet resultset = preparedStatement.executeQuery()){
                if(resultset.next()){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean remove(Integer number) {
       if(isExist(number)){
        String sql = "DELETE FROM todolist WHERE id = ?";
            try (Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                        preparedStatement.setInt(1, number);
                        preparedStatement.executeUpdate();
                        return true;
            } catch (SQLException e) {
                    throw new RuntimeException(e);
            }
        }else{
            return false;
        }
    }
}
