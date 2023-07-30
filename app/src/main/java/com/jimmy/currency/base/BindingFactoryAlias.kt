package com.jimmy.currency.base

import android.view.LayoutInflater
import android.view.ViewGroup

typealias BindingFactory<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
