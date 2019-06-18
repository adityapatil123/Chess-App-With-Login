import kotlin.math.absoluteValue

class Pawn(color: Color, id:Int):AnyCoin(color,id) {
    override fun move(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):MutableMap<ChessPlace,AnyCoin>{
        val placeFirst = getPlaceFromCoin(coin)
        val yNew = place.yPlace
        val xNew = place.xPlace
        val yOld = placeFirst.yPlace
        val xOld = placeFirst.xPlace


        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println(placeFirst)
        println(place)
        println(coin.color)
        try {
            val coinPresent=getCoinFromPlace(place)
            if (coin.color == Color.White) {
                if ((yNew == yOld-1) && (xNew-xOld).absoluteValue==1) {
                        return super.move(coinMap, coin, place)
                    }
            }
            else {
                if ((yNew == yOld+1) && (xNew-xOld).absoluteValue==1) {
                    return super.move(coinMap, coin, place)
                }
            }
        }
        catch(e:NoSuchElementException) {
            //No Code
            if (coin.color == Color.White) {
                if ((yNew < yOld) && (xNew == xOld)) {
                    if (yNew == yOld - 1)    //||((yNew==yOld-2)&&(yOld==6)))
                    {
                        return super.move(coinMap, coin, place)
                    }
                    else if ((yNew == yOld - 2) && (yOld == 7)) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld, yOld - 1))
                        } catch (e: NoSuchElementException) {
                            println("here")
                            return super.move(coinMap, coin, place)
                        }
                        messageToBeDisplayed = "Cant move when someone is in between"
                        return coinMap
                    }

                }
            } else {
                if ((yNew > yOld) && (xNew == xOld)) {
                    if (yNew == yOld + 1)    //||((yNew==yOld-2)&&(yOld==6)))
                    {
                        return super.move(coinMap, coin, place)
                    }
                    else if ((yNew == yOld + 2) && (yOld == 2)) {
                        try {
                            getCoinFromPlace(ChessPlace(xOld, yOld + 1))
                        } catch (e: NoSuchElementException) {
                            return super.move(coinMap, coin, place)
                        }
                        messageToBeDisplayed="Cant move when someone is in between"
                        return coinMap
                    }

                }
            }
        }
        messageToBeDisplayed="Pawn moves by 2, after that by 1 straight And kills cross by 1"
        return coinMap
    }

    override fun getID():String {
        if(color==Color.Black)
            return("BP"+id.toString())
        else
            return("WP"+id.toString())
    }

    override fun check(coinMap:MutableMap<ChessPlace, AnyCoin>, coin: AnyCoin,place: ChessPlace):Boolean
    {
        /*val placeFirst=getPlaceFromCoin(coin)
        coinMap.remove(placeFirst)
        coinMap[place]=coin
        return coinMap */

        val placeFirst = getPlaceFromCoin(coin)
        val yNew = place.yPlace
        val xNew = place.xPlace
        val yOld = placeFirst.yPlace
        val xOld = placeFirst.xPlace

            if (coin.color == Color.White) {
                if ((yNew == yOld - 1) && (xNew - xOld).absoluteValue == 1)
                    return true
            }
            else {
                if ((yNew == yOld+1) && (xNew-xOld).absoluteValue==1)
                    return true
            }
        return false
    }
}
