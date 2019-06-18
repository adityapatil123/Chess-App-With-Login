var coinMap = mutableMapOf<ChessPlace,AnyCoin>()
//var coinMapNew = mutableMapOf<ChessPlace,AnyCoin>()
//val firstArrangement = mutableListOf<Pair<String,Array<Int>>>(
//    Pair("BR1", arrayOf(1,1)),
//    Pair("BK1", arrayOf(2,1)),
//    Pair("BB1", arrayOf(3,1)),
//    Pair("BQ1", arrayOf(4,1)),
//    Pair("BS1", arrayOf(5,1)),
//    Pair("BB2", arrayOf(6,1)),
//    Pair("BK2", arrayOf(7,1)),
//    Pair("BR2", arrayOf(8,1)),
//
//    Pair("BP1", arrayOf(1,2)),
//    Pair("BP2", arrayOf(2,2)),
//    Pair("BP3", arrayOf(3,2)),
//    Pair("BP4", arrayOf(4,2)),
//    Pair("BP5", arrayOf(5,2)),
//    Pair("BP6", arrayOf(6,2)),
//    Pair("BP7", arrayOf(7,2)),
//    Pair("BP8", arrayOf(8,2)),
//
//    Pair("WP1", arrayOf(1,7)),
//    Pair("WP2", arrayOf(2,7)),
//    Pair("WP3", arrayOf(3,7)),
//    Pair("WP4", arrayOf(4,7)),
//    Pair("WP5", arrayOf(5,7)),
//    Pair("WP6", arrayOf(6,7)),
//    Pair("WP7", arrayOf(7,7)),
//    Pair("WP8", arrayOf(8,7)),
//
//    Pair("WR1", arrayOf(1,8)),
//    Pair("WK1", arrayOf(2,8)),
//    Pair("WB1", arrayOf(3,8)),
//    Pair("WQ1", arrayOf(4,8)),
//    Pair("WS1", arrayOf(5,8)),
//    Pair("WB2", arrayOf(6,8)),
//    Pair("WK2", arrayOf(7,8)),
//    Pair("WR2", arrayOf(8,8))
//
//)
//
//
//fun placeAllElements():MutableMap<ChessPlace, AnyCoin> {
//
//    firstArrangement.forEach { pair ->
//        val coin = getCoin(pair.first)
//        val place = getPlace(pair.second)
//        coinMap[place]=coin
//     }
//
//    printTable(coinMap)
//    return coinMap
//}

fun printTable(coinMap:MutableMap<ChessPlace, AnyCoin>)
{
    for(j in 1..8)
    {
        for(i in 1..8)
            try {
                print(coinMap.getValue(getPlace(arrayOf(i, j))).getID() + " ")
            }
            catch(e:Exception){
                print("    ")
            }
        println(" ")
    }
}




/*
coinMap.forEach{
        print(it.key.getID()+" ")
    }
*/
