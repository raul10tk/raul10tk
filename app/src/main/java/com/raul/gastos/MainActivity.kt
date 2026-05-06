package com.raul.gastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.raul.gastos.ui.AddGastoSheet
import com.raul.gastos.ui.GastoViewModel
import com.raul.gastos.ui.HomeScreen
import com.raul.gastos.ui.theme.GastosTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: GastoViewModel by viewModels { GastoViewModel.Factory }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastosTheme {
                val estado by viewModel.estado.collectAsState()
                var sheetVisible by remember { mutableStateOf(false) }
                val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                val scope = rememberCoroutineScope()

                HomeScreen(
                    estado = estado,
                    onAgregarClick = { sheetVisible = true },
                    onEliminar = viewModel::eliminar
                )

                if (sheetVisible) {
                    AddGastoSheet(
                        sheetState = sheetState,
                        onDismiss = { sheetVisible = false },
                        onGuardar = { cantidad, categoria, nota ->
                            viewModel.agregar(cantidad, categoria, nota)
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) sheetVisible = false
                            }
                        }
                    )
                }
            }
        }
    }
}
