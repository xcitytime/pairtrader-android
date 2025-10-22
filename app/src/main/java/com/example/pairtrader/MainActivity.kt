package com.example.pairtrader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pairtrader.core.BinanceApi
import com.example.pairtrader.core.Backtester
import com.example.pairtrader.data.Prefs
import com.example.pairtrader.alerts.AlertWorker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppTabs()
            }
        }
    }
}

@Composable
fun AppTabs() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Live", "Backtest", "Alerts")

    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { i, title ->
                Tab(
                    selected = tabIndex == i,
                    onClick = { tabIndex = i },
                    text = { Text(title) }
                )
            }
        }
        when (tabIndex) {
            0 -> LiveScreen()
            1 -> BacktestScreen()
            2 -> AlertsScreen()
        }
    }
}

@Composable
fun LiveScreen() {
    var symbol1 by remember { mutableStateOf("SEIUSDT") }
    var symbol2 by remember { mutableStateOf("ETHFIUSDT") }
    var output by remember { mutableStateOf("Results will appear here...") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = symbol1, onValueChange = { symbol1 = it }, label = { Text("Symbol 1") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = symbol2, onValueChange = { symbol2 = it }, label = { Text("Symbol 2") })
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            output = BinanceApi.fetchAndCompute(symbol1, symbol2)
        }) {
            Text("Fetch & Compute")
        }
        Spacer(Modifier.height(16.dp))
        Text(output)
    }
}

@Composable
fun BacktestScreen() {
    var result by remember { mutableStateOf("No backtest run yet.") }
    Column(Modifier.padding(16.dp)) {
        Button(onClick = {
            result = Backtester.runDummy()
        }) {
            Text("Run Backtest")
        }
        Spacer(Modifier.height(16.dp))
        Text(result)
    }
}

@Composable
fun AlertsScreen() {
    var status by remember { mutableStateOf("Alerts stopped.") }
    Column(Modifier.padding(16.dp)) {
        Button(onClick = {
            status = "Alerts started (simulated)."
        }) { Text("Start Alerts") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            status = "Alerts stopped."
        }) { Text("Stop Alerts") }
        Spacer(Modifier.height(16.dp))
        Text(status)
    }
}
