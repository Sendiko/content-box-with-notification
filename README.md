# ContentBoxWithNotification

The ContentBoxWithNotification will provide a box that pops down from the top of the screen based on the current screen's state, It is recommended to use ```LaunchedEffect``` to handle state changes.

<br>

# Example
<br>

Loading
---
![Screenshot_20240716-092504](https://github.com/user-attachments/assets/274bf1df-a185-4461-b9c6-acc82fa4fa57)

Error
---
![Screenshot_20240716-092511](https://github.com/user-attachments/assets/a08723d6-9bad-4ad4-8e8b-a2252674b989)

Message
---
![Screenshot_20240716-092519](https://github.com/user-attachments/assets/c38212d5-16f9-4d48-a330-2dc391ee16d2)



<br>

# Example usage

```kotlin
    @Composable
    fun ExampleScreen(
        modifier: Modifier = Modifier,
        state: ExampleScreenState,
        onEvent: (ExampleScreenEvent) -> Unit
    ) {
        LaunchedEffect(
            key = state,
            block = {
                if(state.isError) {
                    delay(1000)
                    onEvent(ExampleScreenEvent.ClearState)
                } 

                if(state.message.isNotBlank) {
                    delay(1000)
                    onEvent(ExampleScreenEvent.ClearState)
                }
            }
        )

        ContentBoxWithNotification(
            message = state.message,
            isLoading state.isLoading
            isErrorNotification = state.isError
        ) {
            ... Rest of composables screen
        }
    }
 ```
