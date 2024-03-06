package com.example.proyecto2grupo12.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto2grupo12.R
import com.example.proyecto2grupo12.domain.profile.entity.CreateUserProfile
import com.example.proyecto2grupo12.domain.profilecard.entity.CurrentProfile
import com.example.proyecto2grupo12.domain.profilecard.entity.NewMatch
import com.example.proyecto2grupo12.domain.profilecard.entity.Profile
import com.example.proyecto2grupo12.extensions.allowProfileGeneration
import com.example.proyecto2grupo12.extensions.getRandomProfile
import com.example.proyecto2grupo12.ui.components.*
import com.example.proyecto2grupo12.ui.theme.Green1
import com.example.proyecto2grupo12.ui.theme.Green2
import com.example.proyecto2grupo12.ui.theme.Orange
import com.example.proyecto2grupo12.ui.theme.Pink
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HomeView(
    uiState: HomeUiState,
    navigateToEditProfile: () -> Unit,
    navigateToMatchList: () -> Unit,
    navigateToNewMatch: () -> Unit,
    setLoading: () -> Unit,
    removeLastProfile: () -> Unit,
    fetchProfiles: () -> Unit,
    swipeUser: (Profile, Boolean) -> Unit,
    createProfiles: (List<CreateUserProfile>) -> Unit,
    newMatch: SharedFlow<NewMatch>,
    currentProfile: SharedFlow<CurrentProfile>,
    setMatch: (NewMatch) -> Unit,
    setCurrentProfile: (CurrentProfile) -> Unit
    ) {
    var showGenerateProfilesDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit, block = {
        newMatch.collect {
            setMatch(it)
            navigateToNewMatch()
        }
    })
    LaunchedEffect(key1 = Unit, block = {
        currentProfile.collect{
            setCurrentProfile(it)
        }
    })
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TopBarIcon(
                    imageVector = Icons.Filled.AccountCircle,
                    onClick = navigateToEditProfile
                )
                Spacer(Modifier.weight(1f))
                TopBarIcon(resId = R.drawable.tinder_logo, modifier = Modifier.size(32.dp))
                Spacer(Modifier.weight(1f))
                TopBarIcon(
                    resId = R.drawable.ic_baseline_message_24,
                    onClick = navigateToMatchList
                )
            }
        },
        floatingActionButton = {
            if (allowProfileGeneration) {
                FloatingActionButton(
                    modifier = Modifier.size(40.dp),
                    onClick = { showGenerateProfilesDialog = true }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            } else Unit
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(uiState){
                is HomeUiState.Error-> {
                    Spacer(Modifier.weight(1f))
                    Text(modifier = Modifier.padding(horizontal = 8.dp),
                        text = uiState.message ?: "", color = Color.Gray, fontSize = 16.sp, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(12.dp))
                    GradientButton(onClick = {
                        scope.launch {
                            delay(200)
                            fetchProfiles()
                        }
                    }) {
                        Text(stringResource(id = R.string.retry))
                    }
                    Spacer(Modifier.weight(1f))
                }
                HomeUiState.Loading -> {
                    Spacer(Modifier.weight(1f))
                    AnimatedGradientLogo(Modifier.fillMaxWidth(.4f))
                    Spacer(Modifier.weight(1f))
                }
                is HomeUiState.Success -> {
                    Spacer(Modifier.weight(1f))
                    Box(Modifier.padding(horizontal = 12.dp)) {
                        Text(
                            text = stringResource(id = R.string.no_more_profiles),
                            color = Color.Gray,
                            fontSize = 20.sp
                        )
                        val localDensity = LocalDensity.current
                        var buttonRowHeightDp by remember { mutableStateOf(0.dp) }

                        val swipeStates = uiState.profileList.map { rememberSwipeableCardState() }
                        uiState.profileList.forEachIndexed { index, profile ->
                            ProfileCardView(profile,
                                modifier = Modifier.swipableCard(
                                    state = swipeStates[index],
                                    onSwiped = {
                                        swipeUser(
                                            profile,
                                            it == SwipingDirection.Right
                                        )
                                        removeLastProfile()
                                    }
                                ),
                                contentModifier = Modifier.padding(bottom = buttonRowHeightDp.plus(8.dp))
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(vertical = 10.dp)
                                .onGloballyPositioned { coordinates ->
                                    buttonRowHeightDp =
                                        with(localDensity) { coordinates.size.height.toDp() }
                                },
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Spacer(Modifier.weight(1f))
                            RoundGradientButton(
                                onClick = {
                                    scope.launch {
                                        swipeStates.last().swipe(SwipingDirection.Left)
                                        removeLastProfile()
                                    }
                                },
                                enabled = swipeStates.isNotEmpty(),
                                imageVector = Icons.Filled.Close, color1 = Pink, color2 = Orange
                            )
                            Spacer(Modifier.weight(.5f))
                            RoundGradientButton(
                                onClick = {
                                    scope.launch {
                                        swipeStates.last().swipe(SwipingDirection.Right)
                                        removeLastProfile()
                                    }
                                },
                                enabled = swipeStates.isNotEmpty(),
                                imageVector = Icons.Filled.Favorite,
                                color1 = Green1,
                                color2 = Green2
                            )
                            Spacer(Modifier.weight(1f))
                        }
                    }

                    Spacer(Modifier.weight(1f))
                }
            }
        }

        if (showGenerateProfilesDialog) {
            val context = LocalContext.current
            GenerateProfilesDialog(
                onDismissRequest = { showGenerateProfilesDialog = false },
                onGenerate = { profileCount ->
                    showGenerateProfilesDialog = false
                    scope.launch(Dispatchers.Main) {
                        setLoading()
                        val profiles = withContext(Dispatchers.IO) {
                            (0 until profileCount).map {
                                async {
                                    getRandomProfile(context)
                                }
                            }.awaitAll()
                        }
                        createProfiles(profiles)
                    }
                }
            )
        }
    }
}

