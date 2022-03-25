package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.view.atoms.Texts
import jp.kaleidot725.emomemo.view.molecules.OkAndCancelButtons
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddNotebookDialog(viewModel: AddNotebookViewModel, onClose: () -> Unit) {
    val uiState by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest {
            when (it) {
                AddNotebookSideEffect.Close -> onClose.invoke()
            }
        }
    }

    Surface(shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
        ) {
            Texts.TitleLarge(text = stringResource(id = R.string.add_notebook_title))

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.notebookTitle,
                onValueChange = { viewModel.updateNotebookTitle(it) },
                label = {
                    Text(text = stringResource(id = R.string.add_notebook_field_title))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OkAndCancelButtons(
                okText = stringResource(id = R.string.add_notebook_ok),
                onOk = { viewModel.ok() },
                enabledOk = uiState.canCreate,
                cancelText = stringResource(id = R.string.add_notebook_cancel),
                enabledCancel = true,
                onCancel = { viewModel.cancel() },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
            )
        }
    }
}