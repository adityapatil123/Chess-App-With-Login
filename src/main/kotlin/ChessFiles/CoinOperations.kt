interface CoinOperations{
    fun move(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):MutableMap<ChessPlace,AnyCoin>
    {

        if(AreConditionsFullfilling(coin,place)==true) {

//            try {
//                val coinPresent= getCoinFromPlace(place)
//            }
//            catch(e:NoSuchElementException)
//            {
//                // No Code
//            }
            val placeFirst=getPlaceFromCoin(coin)
            coinMap.remove(placeFirst)
            coinMap[place]=coin
            isMoved = true
            return coinMap
        }
        return coinMap
    }

    fun check(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):Boolean
    fun getID():String
}


fun AreConditionsFullfilling(coin: AnyCoin,place: ChessPlace):Boolean
{

    if(isCoinPresentAtDesiredLocation(place)) {
        val coinFromPlace = getCoinFromPlace(place)
        if (coin.color == coinFromPlace.color)
        {
            messageToBeDisplayed = "Its your coin only!! Try again"
            println(messageToBeDisplayed)
            return false
        }
    }
    return true
}
