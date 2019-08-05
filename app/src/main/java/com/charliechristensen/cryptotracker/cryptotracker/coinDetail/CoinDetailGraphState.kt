package com.charliechristensen.cryptotracker.cryptotracker.coinDetail

import com.charliechristensen.cryptotracker.data.models.graph.CoinHistoryCandlestickEntry
import com.charliechristensen.cryptotracker.data.models.graph.CoinHistoryGraphEntry
import com.charliechristensen.cryptotracker.data.models.ui.ValueChangeColor

/**
 * Created by Chuck on 1/16/2018.
 */
sealed class CoinDetailGraphState {
    data class Success(
        val coinHistoryList: List<CoinHistoryGraphEntry>,
        val color: ValueChangeColor,
        val startingPrice: Double
    ) : CoinDetailGraphState()

    object Loading : CoinDetailGraphState()
    object NoData : CoinDetailGraphState()
    object Error : CoinDetailGraphState()
}

sealed class CandlestickCoinDetailGraphState {
    data class Success(
        val coinHistoryList: List<CoinHistoryCandlestickEntry>,
        val color: ValueChangeColor,
        val startingPrice: Double
    ) : CandlestickCoinDetailGraphState()

    object Loading : CandlestickCoinDetailGraphState()
    object NoData : CandlestickCoinDetailGraphState()
    object Error : CandlestickCoinDetailGraphState()
}