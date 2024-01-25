package entity;

public class Todolist{
    private Integer id;
    
    private String todo;

    public Todolist() {
    }

    /**
     * constructor ke-2 berperan untuk menyimpan data yang dikirim, class diubah menjadi bentuk array
     * @param todo
     */
    public Todolist(String todo) {
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
