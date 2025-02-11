package com.sanai.gokart.data.repository.datasourceImpl

import com.sanai.gokart.data.db.dao.ProductDAO
import com.sanai.gokart.data.repository.datasource.GoKartLocalDataSource
import kotlinx.coroutines.flow.Flow

class GoKartLocalDataSourceImpl(private val dao: ProductDAO) : GoKartLocalDataSource {


}