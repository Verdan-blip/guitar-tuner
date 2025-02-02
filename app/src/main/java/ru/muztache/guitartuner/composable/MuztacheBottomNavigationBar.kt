package ru.muztache.guitartuner.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import ru.muztache.guitartuner.navigation.BottomNavRoute
import ru.muztache.guitartuner.navigation.getBottomNavRoutes

@Composable
fun MuztacheBottomNavigationBar(
    modifier: Modifier = Modifier,
    routes: List<BottomNavRoute>,
    selectedIndex: MutableIntState,
    navHostController: NavHostController
) {
    NavigationBar {
        getBottomNavRoutes().forEachIndexed { index, route ->
            NavigationBarItem(
                selected = index == selectedIndex.intValue,
                onClick = {
                    navHostController.navigate(route)
                    selectedIndex.intValue = index
                },
                icon = {
                    Icon(
                        painter = painterResource(route.iconId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(route.labelId)
                    )
                }
            )
        }
    }
}