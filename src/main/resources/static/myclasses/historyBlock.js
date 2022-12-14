function createHistoryBlock(item, filler) {

    var baseBlock = document.createElement('div')
    baseBlock.className = 'calcelementulHistory'

    var infoBlock = document.createElement('div')
    infoBlock.className = 'dateBlockV2'

    var monthName = document.createElement('h2')
    monthName.className = 'dateTime newNoteFonts fontXxxLarge fonts'
    monthName.style = 'color: rgb(169, 169, 169); margin: 1vh 0;'

    var monthSummary = document.createElement('p')
    monthSummary.className = 'dateTime fontXxxLarge fonts'
    monthSummary.style = 'color: white; margin: 1vh 0;'

    monthName.innerHTML = item.monthName
    monthSummary.innerHTML = item.summury + ' руб.'

    infoBlock.prepend(monthName, monthSummary)
    baseBlock.prepend(infoBlock)
    filler.prepend(baseBlock)
}
