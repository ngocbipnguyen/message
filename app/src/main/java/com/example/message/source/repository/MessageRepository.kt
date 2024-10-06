package com.example.message.source.repository

import com.example.message.source.sources.remote.RemoteSource
import javax.inject.Inject

class MessageRepository @Inject constructor(val remoteSource: RemoteSource)  {
}