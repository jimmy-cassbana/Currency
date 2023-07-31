package com.jimmy.currency.util

import android.view.LayoutInflater
import android.view.ViewGroup

typealias BindingFactory<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
