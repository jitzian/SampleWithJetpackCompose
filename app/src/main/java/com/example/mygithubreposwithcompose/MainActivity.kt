package com.example.mygithubreposwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.mygithubreposwithcompose.ui.app.ReposApp
import com.example.mygithubreposwithcompose.ui.navigation.NavigationConfig
import com.example.mygithubreposwithcompose.ui.screens.main.MainScreenState
import com.example.mygithubreposwithcompose.ui.screens.main.MainViewModel
import com.example.mygithubreposwithcompose.ui.theme.MyGithubReposWithComposeTheme

@ExperimentalMaterialApi
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //Greeting("Android")asd
            //StateInputView()
            //MainScreenState(user = "josro0ck")
            NavigationConfig()

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyGithubReposWithComposeTheme {
        Greeting("Android")
    }
}

class InputViewModel : ViewModel() {
    private val _input by lazy {
        MutableLiveData<String>()
    }

    val input: LiveData<String>
        get() = _input

    fun setInput(input: String) {
        _input.value = input
    }

    fun onInputChange(newInput: String) {
        _input.value = newInput
    }
}

@Composable
fun StateInputView(inputViewModel: InputViewModel = viewModel()) {
//    var input by rememberSaveable {
//        mutableStateOf("")
//    }

    val input by inputViewModel.input.observeAsState("")

    InputView(
        name = input,
        onTextChanged = {
            inputViewModel.onInputChange(it)
        }
    )
}

@Composable
fun InputView(name: String, onTextChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            if (name.isNotEmpty()) {
                Text(
                    text = "Hello, $name",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1
                )
            }
            OutlinedTextField(
                value = name,
                onValueChange = onTextChanged,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Type something")
                }
            )
        }
    }
}