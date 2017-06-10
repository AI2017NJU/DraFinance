# coding: utf-8
import MySQLdb
import requests
from lxml import etree, html

db = MySQLdb.connect(host="139.199.90.26", user="root", passwd="root", db="finance", charset="utf8")
cursor = db.cursor()


def insert_data(url, symbol):
    print url
    content = requests.get(url)

    tree = etree.HTML(content.text)

    count = 2

    meta = []
    data_list = []

    sql_insert = 'REPLACE into dayK (symbol, dataTime, `open`, high, `close`, low, dealAmount, dealValue) ' \
                 'values (%s, %s, %s, %s, %s, %s, %s, %s)'

    while 1:
        nodes = tree.xpath('//*[@id="FundHoldSharesTable"]/tr[%d]' % count)

        if len(nodes) == 0:
            break

        node = nodes[0]

        date = str.strip(node.xpath('td[1]/div/a/text()')[0])
        open = str.strip(node.xpath('td[2]/div/text()')[0])
        high = str.strip(node.xpath('td[3]/div/text()')[0])
        close = str.strip(node.xpath('td[4]/div/text()')[0])
        low = str.strip(node.xpath('td[5]/div/text()')[0])
        dealAmount = str.strip(node.xpath('td[6]/div/text()')[0])
        dealValue = str.strip(node.xpath('td[7]/div/text()')[0])

        meta.append(symbol)
        meta.append(date)
        meta.append(open)
        meta.append(high)
        meta.append(close)
        meta.append(low)
        meta.append(dealAmount)
        meta.append(dealValue)

        data_list.append(meta)
        meta = []

        # print sql_insert % (symbol, date, open, high, close, low, dealValue, dealAmount)

        count += 1

    cursor.executemany(sql_insert, tuple(data_list))
    db.commit()

    # //*[@id="FundHoldSharesTable"]/tbody/tr[39] //*[@id="FundHoldSharesTable"]/tbody/tr[2]/td[1]/div

    # // *[ @ id = "FundHoldSharesTable"] / tbody / tr[2]


def get_stock():
    cursor.execute('select symbol from stockInfo where symbol < "SH600000"')
    symbols = list(cursor.fetchall())

    url = 'http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/%s/type/S.phtml?year=%s&jidu=%s'

    for symbolPair in symbols:
        symbol = symbolPair[0]
        print '###### stock %s starts scraping ######' % symbol
        for season in range(1, 5):
            insert_data(url % (symbol[2: 8], 2016, season), symbol)
        for season in range(1, 3):
            insert_data(url % (symbol[2: 8], 2017, season), symbol)

get_stock()
cursor.close()
db.close()
