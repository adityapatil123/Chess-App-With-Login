import kotlin.math.absoluteValue

class Queen(color: Color, id:Int):AnyCoin(color,id){
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
        var a=0

        //RookCode
        if((yNew!=yOld)&&(xNew==xOld))
        {
            a= yNew-yOld
            if(a > 0) {
                for (i in 1..a-1) {
                    try {
                        getCoinFromPlace(ChessPlace(xOld, yNew - i))
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
                        getCoinFromPlace(ChessPlace(xOld, yNew - i))
                        messageToBeDisplayed = "Someone is in between"
                        return coinMap
                    }
                    catch(e:NoSuchElementException) {
                        //No Code
                    }

                }
            }
            return super.move(coinMap, coin, place)
        }
        else if((xNew!=xOld)&&(yNew==yOld))
        {
            a= xNew-xOld
            if(a > 0) {
                for (i in 1..a - 1) {
                    try {
                        getCoinFromPlace(ChessPlace(xNew-i, yOld))
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
                        getCoinFromPlace(ChessPlace(xNew-i, yOld))
                        messageToBeDisplayed = "Someone is in between"
                        return coinMap
                    }
                    catch(e:NoSuchElementException) {
                        //No Code
                    }

                }
            }
            return super.move(coinMap, coin, place)
        }

        //Bishop Code
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

        messageToBeDisplayed = "Queen can move like both Rook and Bishop"
        return coinMap
    }

    override fun getID():String {
        if(color==Color.Black)
            return("BQ"+id.toString())
        else
            return("WQ"+id.toString())
    }

    override fun check(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):Boolean
    {
        val placeFirst = getPlaceFromCoin(coin)
        val yNew = place.yPlace
        val xNew = place.xPlace
        val yOld = placeFirst.yPlace
        val xOld = placeFirst.xPlace

        //Bishop Code
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

        //Rook Code
        var a=0
        if((yNew!=yOld)&&(xNew==xOld))
        {
            a= yNew-yOld
            if(a > 0) {
                for (i in 1..a-1) {
                    try {
                        getCoinFromPlace(ChessPlace(xOld, yNew - i))
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
                        getCoinFromPlace(ChessPlace(xOld, yNew - i))
                        return false
                    }
                    catch(e:NoSuchElementException) {
                        //No Code
                    }

                }
            }
            return true
        }
        else if((xNew!=xOld)&&(yNew==yOld))
        {
            a= xNew-xOld
            if(a > 0) {
                for (i in 1..a - 1) {
                    try {
                        getCoinFromPlace(ChessPlace(xNew-i, yOld))
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
                        getCoinFromPlace(ChessPlace(xNew-i, yOld))
                        return false
                    }
                    catch(e:NoSuchElementException) {
                        //No Code
                    }

                }
            }
            return true
        }
        return false
    }
}