package pl.wsei.lab06.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.wsei.lab06.TodoTask
import pl.wsei.lab06.data.entities.TodoTaskDao
import pl.wsei.lab06.data.entities.TodoTaskEntity

interface TodoTaskRepository {
    fun getAllAsStream(): Flow<List<TodoTask>>
    fun getItemAsStream(id: Int): Flow<TodoTask?>
    suspend fun insertItem(item: TodoTask)
    suspend fun deleteItem(item: TodoTask)
    suspend fun updateItem(item: TodoTask)
}
class DatabaseTodoTaskRepository(val dao: TodoTaskDao) : TodoTaskRepository {

    override fun getAllAsStream(): Flow<List<TodoTask>> {
        return dao.findAll().map { it ->
            it.map {
                it.toModel()
            }
        }
    }

    override fun getItemAsStream(id: Int): Flow<TodoTask?> {
        return dao.find(id).map {
            it.toModel()
        }
    }

    override suspend fun insertItem(item: TodoTask) {
        dao.insertAll(TodoTaskEntity.fromModel(item))
    }

    override suspend fun deleteItem(item: TodoTask) {
        dao.removeById(TodoTaskEntity.fromModel(item))
    }

    override suspend fun updateItem(item: TodoTask) {
        TODO("Not yet implemented")
    }
}