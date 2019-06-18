enum class CoinType(val type:String)
{
    Pawn("P"),
    Rook("R"),
    Knight("K"),
    Bishop("B"),
    Queen("Q"),
    King("S");

    companion object{
        fun instance(type:String):CoinType
        {
            CoinType.values().forEach {
                if (it.type == type) return it
            }
            throw Exception("Invalid Coin Type")
        }
    }
}

enum class Color(val color:String)
{
    White("W"),
    Black("B");

    companion object{
        fun instance(color:String):Color
        {
            Color.values().forEach {
                if (it.color == color) return it
            }
            throw Exception("Invalid Color")
        }
    }
}


fun validID(coinType: CoinType,str:String):Int
{

    val num = Integer.parseInt(str)
    when(coinType){
        CoinType.Pawn ->{
            if(num>=1 && num<=8) return num
        }
        CoinType.Queen,CoinType.King ->{
            if(num == 1) return num
        }
        else -> {
            if (num == 1 || num == 2) return num
        }
    }
    throw Exception("Invalid IDs")
}

fun getCoin(coinID:String):AnyCoin
{
    if(coinID.length==3)
    {

        val color = Color.instance(coinID[0].toString())
        val coinType = CoinType.instance(coinID[1].toString())
        val id = validID(coinType,coinID[2].toString())

        when(coinType){
            CoinType.Pawn -> return Pawn(color,id)
            CoinType.Rook -> return Rook(color,id)
            CoinType.Knight -> return Knight(color,id)
            CoinType.Bishop -> return Bishop(color,id)
            CoinType.Queen -> return Queen(color,id)
            CoinType.King -> return King(color,id)
        }
    }
    throw Exception("Invalid CoinID")
}

fun getCoinFromPlace(chessPlace: ChessPlace):AnyCoin
{
        return coinMap.getValue(chessPlace)
}

fun isCoinPresentAtDesiredLocation(chessPlace: ChessPlace):Boolean
{
    try {
        val coin = coinMap.getValue(chessPlace)
        return true
    }
    catch(e:NoSuchElementException){
        return false
    }
}



abstract class AnyCoin(val color: Color,val id:Int):CoinOperations



