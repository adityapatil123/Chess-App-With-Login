import patil.aditya.login.MoveResponse

var count = 0
var isMoved = false
var isThereCheck = false
var messageToBeDisplayed = ""

var coinString = ""
var killedCoinString = "NUL"
var sourcePlaceString = ""
var destinationPlaceString = ""

fun userInstr(sourceId:String, destinationId:String):MoveResponse
{
    isMoved = false
    isThereCheck = false
    messageToBeDisplayed = ""

    if (isKingAlive(coinMap)) {
        //Input format: coinId-placec
        coinString = sourceId.slice(0..2)
        sourcePlaceString = sourceId.slice(3..4)
//        destinationPlaceString = destinationId.slice(3..4)
//        killedCoinString = destinationId.slice(0..2)

        val coin = getCoin(coinString)

        var xPlaceNew = Integer.parseInt(destinationId[4].toString()) + 1
        var yPlaceNew = Integer.parseInt(destinationId[3].toString()) + 1
//        val sourceplace = getPlaceUser(xPlaceNew + "" + yPlaceNew)
        destinationPlaceString = "$xPlaceNew$yPlaceNew"
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println("********************************************")
        println(destinationPlaceString)
        val destinationPlace = getPlaceUser(destinationPlaceString)

        coinMap = coin.move(coinMap, coin, destinationPlace)
        isThereCheck = isThereAnyCheck(coinMap)


        printTable(coinMap)
    }
    else println("Program is Over!!!")

    return MoveResponse(isMoved,isThereCheck,isKingAlive(coinMap))
}


