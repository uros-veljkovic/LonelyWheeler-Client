package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.entity.query.QueryEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.QueryApi
import javax.inject.Inject

class QueryService
@Inject
constructor(
    private val queryApi: QueryApi,
) {

    suspend fun makeQuery(queryEntity: QueryEntity): MyResponse<MutableList<OfferEntity>> {
        return queryApi.makeQuery(queryEntity)
    }

}