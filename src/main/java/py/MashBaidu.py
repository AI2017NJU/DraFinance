import random

import MySQLdb
import requests
import time

db = MySQLdb.connect(host="139.199.90.26", user="root", passwd="root", db="finance", charset="utf8")
cursor = db.cursor()

now = time.strftime('%Y%m%d', time.localtime(time.time()))

# urls=["http://www.tianyancha.com/search/%E7%BB%9F%E8%AE%A1.json?&pn="+str(i) for i in range(38,51)]
url = "https://gupiao.baidu.com/api/stocks/stockdaybar?" \
      "from=pc" \
      "&os_ver=1" \
      "&cuid=xxx" \
      "&vv=100" \
      "&format=json" \
      "&stock_code=%s" \
      "&step=3" \
      "&start=" \
      "&count=160" \
      "&fq_type=no" \
      "&timestamp=1496997095363"

UA = ["Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0",
      "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36"]
cookies = ["BAIDUID=BE5DC9496AA7F59CDC87EDE527F3C7FB:FG=1; Hm_lvt_35d1e71f4c913c126b8703586f1d2307=1496996512; "
           "Hm_lpvt_35d1e71f4c913c126b8703586f1d2307=1496997091"]

headers = {
    "Accept": "*/*",
    "Accept-Encoding": "gzip, deflate, sdch, br",
    "Accept-Language": "zh-CN,zh;q=0.8,en;q=0.6,en-US;q=0.4",
    "Cache-Control": "no-cache",
    "Connection": "keep-alive",
    "Cookie": cookies[0],
    "host": "gupiao.baidu.com",
    "Pragma": "no-cache",
    "Referer": "https://gupiao.baidu.com/stock/sh601766.html",
    "User-Agent": random.choice(UA),
    "X-Requested-With": "XMLHttpRequest"
}


def get_data(url, symbol):
    print(url)
    data = requests.get(url=url, headers=headers).json()
    if "mashData" not in data.keys():
        return 0
    data = data["mashData"]
    # print data["mashData"]
#     # print(data)
#
    if len(data) == 0:
        return 0

    for meta in data:
        # print meta
        if now == meta["date"]:
            date = str(meta["date"])[0: 4] + "-" + str(meta["date"])[4: 6] + "-" + str(meta["date"])[6:]
            mash = [symbol, date,
                    meta["ma5"]["volume"], meta["ma5"]["avgPrice"],
                    meta["ma10"]["volume"], meta["ma10"]["avgPrice"],
                    meta["ma20"]["volume"], meta["ma20"]["avgPrice"],
                    meta["macd"]["diff"], meta["macd"]["dea"], meta["macd"]["macd"],
                    meta["kdj"]["k"], meta["kdj"]["d"], meta["kdj"]["j"],
                    meta["rsi"]["rsi1"], meta["rsi"]["rsi2"], meta["rsi"]["rsi3"]]
            insert = "insert ignore into mashData " \
                     "(symbol, dataTime, ma5_volume, ma5_price, ma10_volume, ma10_price, ma20_volume, " \
                     "ma20_price, diff, dea, macd, k, d, j, rsi1, rsi2, rsi3) " \
                     "values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
            cursor.execute(insert, tuple(mash))
            db.commit()
            break

    return 1


def get_stock():
    cursor.execute('select symbol from stockInfo')
    symbols = list(cursor.fetchall())

    for symbolPair in symbols:
        symbol = symbolPair[0]
        print '###### stock %s starts scraping ######' % symbol
        get_data(url % symbol.lower(), symbol)

get_stock()
# print now
# get_data(url % "sz399998", "SZ399998")
cursor.close()
