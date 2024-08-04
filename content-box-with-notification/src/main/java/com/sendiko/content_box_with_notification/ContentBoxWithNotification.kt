package com.sendiko.content_box_with_notification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * Sendiko's custom notification composables!.
 *
 * In addition to this component, also recommended to use LaunchedEffect to set and clear the state of ContentBoxWithNotificaition
 *
 * @param message for the message that will be shown in the notification.
 * @param isErrorNotification error notification will show with red color.
 * @param isLoading show loading indicator notification.
 * @param content for the screen content inside.
 * @param containerColor color for background box.
 * @param errorContainerColor color for background box when error.
 * @param contentColor color for content text.
 * @param contentErrorColor color for content text when error.
 * @param loadingContainerColor color for background box when loading.
 * @param loadingContentColor color for content text when loading.
 *
 * @author Sendiko
 *
 * */
@Composable
fun ContentBoxWithNotification(
    message: String,
    isErrorNotification: Boolean = false,
    isLoading: Boolean = false,
    content: @Composable (() -> Unit),
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    errorContainerColor: Color = MaterialTheme.colorScheme.errorContainer,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    contentErrorColor: Color = MaterialTheme.colorScheme.onErrorContainer,
    loadingContainerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    loadingContentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    textStyle: TextStyle = TextStyle()
) {
    Box {
        content()
        Notification(
            message = message,
            isVisible = message.isNotBlank() && !isLoading,
            isErrorNotification = isErrorNotification,
            errorContainerColor = errorContainerColor,
            containerColor = containerColor,
            contentColor = contentColor,
            contentErrorColor = contentErrorColor,
            textStyle = textStyle
        )
        LoadingIndicator(
            isLoading = isLoading,
            containerColor = loadingContainerColor,
            contentColor = loadingContentColor,
            textStyle = textStyle
        )
    }
}

/**
 *
 * The notification that will be shown.
 *
 * @param message the message that will be shown.
 * @param isVisible when the notification comes up.
 * @param isErrorNotification shown red notification.
 *
 * */
@Composable
fun Notification(
    message: String,
    isVisible: Boolean,
    isErrorNotification: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    errorContainerColor: Color = MaterialTheme.colorScheme.errorContainer,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    contentErrorColor: Color = MaterialTheme.colorScheme.onErrorContainer,
    textStyle: TextStyle = TextStyle()
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isErrorNotification) errorContainerColor else containerColor),
            content = {
                Text(
                    text = message,
                    modifier = Modifier.padding(top = 28.dp + 8.dp, end = 8.dp, start = 8.dp, bottom = 16.dp),
                    color = if (isErrorNotification) contentErrorColor else contentColor,
                    style = textStyle
                )
            }
        )
    }
}

/**
 *
 * The loading indicator
 *
 * @param isLoading to determine if loading indicator should show.
 *
 * */
@Composable
fun LoadingIndicator(
    isLoading: Boolean,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    textStyle: TextStyle = TextStyle()
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerColor),
            content = {
                Row(
                    modifier = Modifier.padding(top = 28.dp + 8.dp, end = 8.dp, start = 8.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Loading",
                        color = contentColor,
                        style = textStyle
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 1.dp,
                        color = contentColor
                    )
                }
            }
        )
    }
}