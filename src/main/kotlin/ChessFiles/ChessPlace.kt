



data class ChessPlace(
    val xPlace: Int,
    val yPlace: Int
)

fun getPlace(placeId:Array<Int>): ChessPlace {

    if(placeId.size==2) {
        val xPlace = checkPlace(placeId[0])
        val yPlace = checkPlace(placeId[1])

        return ChessPlace(xPlace,yPlace)
    }
    throw Exception("Invalid Length")

}

fun getPlaceUser(placeId:String):ChessPlace{

    if(placeId.length==2)
    {
        val xPlace=checkPlace(Integer.parseInt(placeId[0].toString()))
        val yPlace=checkPlace(Integer.parseInt(placeId[1].toString()))
        return ChessPlace(xPlace,yPlace)
    }
    throw Exception("Invalid Length")
}

fun checkPlace(num: Int): Int {
    if (num >= 1 && num <= 8) return num
    throw Exception("Invalid Place")
}


fun getPlaceFromCoin(coinAsked: AnyCoin):ChessPlace
{
    try {
        for ((key, value1) in coinMap) {
            if (coinAsked.getID() == value1.getID()) {
                return key
            }
        }
    }
    catch(e:Exception)
    {
        println("Invalid input")
    }
    return ChessPlace(1,1)
}

