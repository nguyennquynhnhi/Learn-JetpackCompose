/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codelabs.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
//        var count by remember { mutableStateOf(0) }
        // sử dụng 'by' thay cho '=' giúp khỏi sdung '.value' mỗi lần cần dùng biên
        //dùng 'remember' để ghi nhớ state, tránh việc bị reset gtri mỗi lần UI update

        var count by rememberSaveable { mutableStateOf(0) }
        // 'remember' giữ lại state qua các lần kết hợp lại, nhưng state không được giữ lại khi
        //thay đổi cấu hình => sdung 'rememberSaveable' thay cho 'remember'
        //Bên cạnh việc giữ lại trạng thái qua các lần tái cấu trúc, rememberSaveable cũng giữ lại
        //trạng thái trên quá trình tạo lại Activity và sự kiện bị buộc tắt do hệ thống gây ra

        if (count > 0) {
            Text("You've had $count glassed.")
        }
        Button(onClick = {count++}, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, {count++}, modifier)
}