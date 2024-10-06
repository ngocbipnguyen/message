package com.example.message.source.sources.remote

import com.example.message.source.sources.AppSource
import javax.inject.Inject


class RemoteSource @Inject constructor(apiInterface: ApiInterface) : AppSource.Remote {
}