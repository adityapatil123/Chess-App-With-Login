import kotlin.math.absoluteValue

class King(color: Color, id:Int):AnyCoin(color,id) {
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
        if((yNew==yOld)&&(xNew-xOld).absoluteValue==1)
            return super.move(coinMap, coin, place)
        else if((xNew==xOld)&&(yNew-yOld).absoluteValue==1)
            return super.move(coinMap, coin, place)
        else if((xNew-xOld).absoluteValue==1 &&(yNew-yOld).absoluteValue==1)
            return super.move(coinMap, coin, place)

        messageToBeDisplayed="King can move anywhere by 1 step"
        return coinMap
    }

    override fun getID():String {
        if(color==Color.Black)
            return("BS"+id.toString())
        else
            return("WS"+id.toString())
    }

    override fun check(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):Boolean {
        val placeFirst = getPlaceFromCoin(coin)
        val yNew = place.yPlace
        val xNew = place.xPlace
        val yOld = placeFirst.yPlace
        val xOld = placeFirst.xPlace

        if((yNew==yOld)&&(xNew-xOld).absoluteValue==1)
            return true
        else if((xNew==xOld)&&(yNew-yOld).absoluteValue==1)
            return true
        else if((xNew-xOld).absoluteValue==1 &&(yNew-yOld).absoluteValue==1)
            return true

        return false
    }
}


fun isThereAnyCheck(coinMap:MutableMap<ChessPlace, AnyCoin>):Boolean {
    if (count % 2 == 1) {
        //White's Time
        var a=0
        val kingPlace=getPlaceFromCoin(getCoin("WS1"))
        coinMap.forEach {key, value ->
            if (value.color==Color.Black) {
                if(value.check(coinMap,value,kingPlace)==true)
                    a=1
            }
        }
        if(a==1)
            return true
        return false
    }
    else{
        //Black's Time
        var a=0
        val kingPlace=getPlaceFromCoin(getCoin("BS1"))
        coinMap.forEach { key, value ->
            if (value.color==Color.White) {
                //val coinMapTemp=coin.move(coinMap,coin,place)

                if(value.check(coinMap,value,kingPlace)==true)
                    a=1
            }
        }
        if(a==1)
            return true
        return false
    }
}

fun isKingAlive(coinMap: MutableMap<ChessPlace, AnyCoin>):Boolean
{
    var a=0
    coinMap.forEach {key, value ->
        if (value.getID()[1].toString()=="S") {
            a++
        }
    }
    if(a==2)
        return true
    return false
}
