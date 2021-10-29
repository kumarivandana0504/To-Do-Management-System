package springbootproject.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springbootproject.todolist.dao.TaskDao;
import springbootproject.todolist.entity.Task;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	@Override
	@Transactional
	public void addTask(Task newTask) {
		taskDao.addTask(newTask);

	}

	@Override
	@Transactional
	public void deleteTask(int taskId) {
		taskDao.deleteTask(taskId);

	}

	@Override
	@Transactional
	public List<Task> getUserTasks(int userId) {
		// TODO Auto-generated method stub
		return taskDao.getUserTasks(userId);
	}

	@Override
	public Optional<Task> getByTaskId(int taskId) {
		
		return taskDao.getByTaskId(taskId);
	}

}
