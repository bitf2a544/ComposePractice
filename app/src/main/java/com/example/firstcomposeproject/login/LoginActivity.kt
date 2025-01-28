package com.example.firstcomposeproject.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.firstcomposeproject.ConversionViewModel
import com.example.thirdcomposeproject.R


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NavigationSystem()
        }
    }
}
@Composable
fun NavigationSystem() {
    val navController = rememberNavController()
    val viewModel: ConversionViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController, viewModel = viewModel)//composable functions
        }
        composable("dashboard") {
            MyAppDashBoard(navController = navController, viewModel = viewModel)//composable functions
        }
        composable("result") {
            ResultScreen(navController = navController, viewModel = viewModel)//composable functions
        }
    }
}

@Composable
fun LoginScreen(navController: NavController, viewModel: ConversionViewModel) {
    var emailEditText by remember { mutableStateOf("") }
    val fahrenheit = emailEditText.toIntOrNull() ?: 0
    var passEditText by remember { mutableStateOf("") }
    if(!viewModel.emailText.isNullOrEmpty()){
        emailEditText = viewModel.emailText
    }
    if(!viewModel.passText.isNullOrEmpty()){
        passEditText = viewModel.passText
    }

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            MyAppToolbar(title = "Login",
            onBackPressed = { Log.e("Helow","World") }) })
    { padding ->

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            LoginField(
                value = emailEditText,
                onChange = { emailEditText = it },
                label = "Login",
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
               /* value = "password",
                onChange = { },*/
                value = passEditText,
                onChange = { passEditText = it },
                label = "Password",
                submit = { },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            LabeledCheckbox(
                label = "Remember Me",
                onCheckChanged = { },
                isChecked = false
            )

            Button(
                onClick = {
                    viewModel.loginApiCall(emailEditText, passEditText)
                    navController.navigate("result")
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}

@Composable
fun ResultScreen(navController: NavController, viewModel: ConversionViewModel) {
    //ConstraintLayoutExample()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Email: ${viewModel.emailText.toString()} Pass = ${viewModel.passText}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))

        Button(onClick = { navController.navigate("dashboard") }) {
            Text(text = "Move To Dashboard")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationSystem()
}

@Composable
fun MyAppToolbar(
    title: String,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 20.sp)
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.app_name)
                )
            }
        },
        backgroundColor = colorResource(R.color.purple_500),
        contentColor = Color.White,
        elevation = AppBarDefaults.TopAppBarElevation
    )
}




@Composable
fun LoginField(  value: String,
                 onChange: (String) -> Unit,
                 modifier: Modifier = Modifier,
                 label: String = "Login",
                 placeholder: String = "Enter your Login"){

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password"
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Lock,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "", tint = MaterialTheme.colorScheme.primary)
        }
    }


    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun LabeledCheckbox(
    label: String,
    onCheckChanged: () -> Unit,
    isChecked: Boolean
) {

    Row(Modifier.clickable(onClick = onCheckChanged).padding(4.dp)) {
        androidx.compose.material3.Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(Modifier.size(6.dp))
        Text(label)
    }
}


