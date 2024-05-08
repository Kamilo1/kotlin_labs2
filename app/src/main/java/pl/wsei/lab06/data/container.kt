package pl.wsei.lab06.data

import android.app.Application
import android.content.Context
import pl.wsei.lab06.data.entities.AppDatabase
import pl.wsei.lab06.data.repository.DatabaseTodoTaskRepository
import pl.wsei.lab06.data.repository.TodoTaskRepository

interface AppContainer {
    val todoTaskRepository: TodoTaskRepository
    // właściwości zwracające wymagane instancje obiektów w aplikacji
}
class AppDataContainer private constructor(private val context: Context): AppContainer{
    //implementacja własciwości
    override val todoTaskRepository: TodoTaskRepository by lazy{
        DatabaseTodoTaskRepository(AppDatabase.getInstance(context).taskDao())
    }
    companion object {
        @Volatile private var instance: AppDataContainer? = null

        fun getInstance(context: Context): AppDataContainer {
            return instance ?: synchronized(this) {
                instance ?: AppDataContainer(context).also { instance = it }
            }
        }
    }
}
