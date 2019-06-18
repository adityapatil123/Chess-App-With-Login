import kotlin.math.absoluteValue

class Bishop(color: Color, id:Int):AnyCoin(color,id) {
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


        if ((yNew - yOld).absoluteValue == (xNew - xOld).absoluteValue) {
            if(yNew-yOld == xOld-xNew)
            {
                val a = yNew-yOld
                if(a > 0) {
                    for (i in 1..a - 1) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld - i, yOld + i))
                            messageToBeDisplayed = "Someone is in between"
                            return coinMap
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
                else{
                    for (i in (-1).downTo(a+1)) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld - i, yOld + i))
                            messageToBeDisplayed = "Someone is in between"
                            return coinMap
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
            }
            else{
                val a = yNew-yOld
                if(a > 0) {
                    for (i in 1..a - 1) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld + i, yOld + i))
                            messageToBeDisplayed = "Someone is in between"
                            return coinMap
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
                else{
                    for (i in (-1).downTo(a+1)) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld + i, yOld + i))
                            messageToBeDisplayed = "Someone is in between"
                            return coinMap
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
            }
            return super.move(coinMap, coin, place)
        }
        messageToBeDisplayed = "Bishop can move in cross-direction only"
        return coinMap
    }

    override fun getID():String {
        if(color==Color.Black)
            return("BB"+id.toString())
        else
            return("WB"+id.toString())
    }

    override fun check(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):Boolean
    {
        val placeFirst = getPlaceFromCoin(coin)
        val yNew = place.yPlace
        val xNew = place.xPlace
        val yOld = placeFirst.yPlace
        val xOld = placeFirst.xPlace

        if ((yNew - yOld).absoluteValue == (xNew - xOld).absoluteValue) {
            if(yNew-yOld == xOld-xNew)
            {
                val a = yNew-yOld
                if(a > 0) {
                    for (i in 1..a - 1) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld - i, yOld + i))
                            return false
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
                else{
                    for (i in (-1).downTo(a+1)) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld - i, yOld + i))
                            return false
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
            }
            else{
                val a = yNew-yOld
                if(a > 0) {
                    for (i in 1..a - 1) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld + i, yOld + i))
                            return false
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
                else{
                    for (i in (-1).downTo(a+1)) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld + i, yOld + i))
                            return false
                        }
                        catch(e:NoSuchElementException) {
                            //No Code
                        }
                    }
                }
            }
            return true
        }
        return false
    }
}
