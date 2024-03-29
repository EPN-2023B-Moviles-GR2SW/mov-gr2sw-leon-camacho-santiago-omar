package com.example.proyecto2grupo12.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyecto2grupo12.R
import com.example.proyecto2grupo12.domain.match.entity.Match
import com.example.proyecto2grupo12.domain.message.entity.Message
import com.example.proyecto2grupo12.extensions.withLinearGradient
import com.example.proyecto2grupo12.ui.components.ChatFooter
import com.example.proyecto2grupo12.ui.theme.AntiFlashWhite
import com.example.proyecto2grupo12.ui.theme.Orange
import com.example.proyecto2grupo12.ui.theme.Pink
import com.example.proyecto2grupo12.ui.theme.UltramarineBlue

@Composable
fun ChatView(
    match: Match,
    onArrowBackPressed: () -> Unit,
    sendMessage: (String) -> Unit,
    messages: List<Message>
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ChatAppBar(match = match, onArrowBackPressed = onArrowBackPressed)
        },
        bottomBar = { ChatFooter(
            onSendClicked = sendMessage
        ) }
    ) { padding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    text = stringResource(id = R.string.you_matched_with_on, match.userName, match.formattedDate).uppercase())
            }
            items(messages.size){ index ->
                MessageItem(match = match,message = messages[index])
            }
        }
    }

}

@Composable
fun ChatAppBar(match: Match, onArrowBackPressed: () -> Unit){
    Surface(elevation = AppBarDefaults.TopAppBarElevation){
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Box(Modifier.weight(1f)){
                IconButton(modifier = Modifier.height(IntrinsicSize.Max),onClick = onArrowBackPressed) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .withLinearGradient(Pink, Orange)
                            .align(Alignment.TopCenter)
                    )
                }
            }

            Column(Modifier.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally){
                AsyncImage(
                    model = match.userPicture,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(text = match.userName, fontSize = 13.sp,fontWeight = FontWeight.Light, color = Color.Gray,textAlign = TextAlign.Center)
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun MessageItem(match: Match, message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if(message.isFromSender) Arrangement.End else Arrangement.Start) {

        if(message.isFromSender){
            Spacer(Modifier.fillMaxWidth(.25f))
        } else {
            AsyncImage(
                model = match.userPicture,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }

        Text(
            modifier = Modifier
                .background(
                    if (message.isFromSender) UltramarineBlue else AntiFlashWhite,
                    RoundedCornerShape(4.dp)
                )
                .padding(6.dp)
                .weight(4f, false)
            ,
            text = message.text,
            color = if(message.isFromSender) Color.White else Color.Black,
        )

        if(!message.isFromSender){
            Spacer(
                Modifier
                    .height(4.dp)
                    .weight(1f, false)
                    .background(Color.Red))
        }

    }
}
