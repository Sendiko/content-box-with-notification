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
 *
 * */
@Composable
fun ContentBoxWithNotification(
    message: String,
    isErrorNotification: Boolean = false,
    isLoading: Boolean = false,
    content: @Composable (() -> Unit),
) {
    Box {
        content()
        Notification(
            message = message,
            isVisible = message.isNotBlank() && !isLoading,
            isErrorNotification = isErrorNotification
        )
        LoadingIndicator(isLoading = isLoading)
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
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isErrorNotification) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surfaceVariant),
            content = {
                Text(
                    text = message,
                    modifier = Modifier.padding(top = 28.dp + 8.dp, end = 8.dp, start = 8.dp, bottom = 16.dp),
                    color = if (isErrorNotification) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onSurfaceVariant,
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
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            content = {
                Row(
                    modifier = Modifier.padding(top = 28.dp + 8.dp, end = 8.dp, start = 8.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Loading",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 1.dp)
                }
            }
        )
    }
}