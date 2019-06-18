import kotlin.math.absoluteValue

class Knight(color: Color, id:Int):AnyCoin(color,id) {
    override fun move(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):MutableMap<ChessPlace,AnyCoin>{
        val placeFirst = getPlaceFromCoin(coin)
        val yNew = place.yPlace
        val xNew = place.xPlace
        val yOld = placeFirst.yPlace
        val xOld = placeFirst.xPlace

        try {
            val coinPresent=getCoinFromPlace(place)
        }
        catch(e:NoSuchElementException) {
            //No Code
        }
        if (((yNew - yOld).absoluteValue ==2) &&((xNew - xOld).absoluteValue==1))
            return super.move(coinMap, coin, place)
        else if (((yNew - yOld).absoluteValue ==1) &&((xNew - xOld).absoluteValue==2))
            return super.move(coinMap, coin, place)

        messageToBeDisplayed = "Knight moves by 2 and half step"
        return coinMap


    }
    override fun getID():String {
        if(color==Color.Black)
            return("BK"+id.toString())
        else
            return("WK"+id.toString())
    }

    override fun check(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):Boolean
    {

        val placeFirst = getPlaceFromCoin(coin)
        val yNew = place.yPlace
        val xNew = place.xPlace
        val yOld = placeFirst.yPlace
        val xOld = placeFirst.xPlace

        if (((yNew - yOld).absoluteValue ==2) &&((xNew - xOld).absoluteValue==1))
            return true
        else if (((yNew - yOld).absoluteValue ==1) &&((xNew - xOld).absoluteValue==2))
            return true
        return false
    }
}

/*
if (((yNew - yOld).absoluteValue ==2) &&((xNew - xOld).absoluteValue==1))
            return super.move(table, element, loc)
        else if (((yNew - yOld).absoluteValue ==1) &&((xNew - xOld).absoluteValue==2))
            return super.move(table, element, loc)
        return table
 */