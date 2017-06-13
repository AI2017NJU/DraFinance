# coding: utf-8
import MySQLdb
import time

import datetime
import numpy as np


db = MySQLdb.connect(host="139.199.90.26", user="root", passwd="root", db="finance", charset="utf8")
cursor = db.cursor()
now = time.strftime('%Y-%m-%d', time.localtime(time.time()))


def nonlin(x, deriv=False):
    if deriv:
        return x*(1-x)
    return 1/(1+np.exp(-x))


def get_predict(trainX, trainY, testX):
    trainX = np.array(trainX)
    trainY = np.array(trainY).T
    syn0 = 2 * np.random.random((10, 1)) - 1

    for iter in xrange(500):
        # forward propagation
        l0 = trainX
        l1 = nonlin(np.dot(l0, syn0))

        # how much did we miss?
        l1_error = trainY - l1

        # multiply how much we missed by the
        # slope of the sigmoid at the values in l1
        l1_delta = l1_error * nonlin(l1, True)

        # update weights
        syn0 += np.dot(l0.T, l1_delta)
        # print "Output After Training:"
        # print l1
        # print syn0
    l1 = 1 / (1 + np.exp(-(np.dot(testX, syn0))))
    # print l1
    return l1[0]


def predict(symbol):
    cursor.execute('select `close`, `dataTime` from dayK where dataTime < "2017"  and `stockid` = "%s"' % symbol)
    raw = list(cursor.fetchall())
    base = []
    for each in raw:
        base.append(each[0])
    actual = []
    date = []
    cursor.execute('select `close`, `dataTime` from dayK where dataTime >= "2017"  and `stockid` = "%s"' % symbol)
    raw = list(cursor.fetchall())

    for each in raw:
        actual.append(each[0])
        date.append(each[1])

    predict = []
    predict_upper = []
    predict_low = []
    X = []
    j = 1
    for each in actual:
        X.append(j)
        j += 1
        trainX = []
        trainY = []
        for x in range(0, len(base)-10):
            tempX = base[x:x+10]
            tempY = base[x+10]
            sum = 0
            for every in tempX:
                sum += every
            for k in range(0, len(tempX)):
                tempX[k] = float(tempX[k]) / float(sum)
            #     if tempX[k] >= 0:
            #         tempX[k] = 1
            #     else:
            #         tempX[k] = 0
            tempY = float(tempY) / float(sum)
            trainX.append(tempX)
            trainY.append(tempY)
        testX = base[-10:]
        sum = 0
        for every in testX:
            sum += every
        for i in range(0, len(testX)):
            testX[i] = float(testX[i]) / float(sum)
        price = get_predict(trainX, trainY, testX)*sum
        var = np.var(np.array(base)) / len(base)
        d = np.sqrt(var)
        predict.append(price)
        predict_upper.append(price+d)
        predict_low.append(price-d)
        base.append(each)

    time = date[-1]
    time = datetime.datetime.strptime(time, "%Y-%m-%d")
    time = time + datetime.timedelta(1)
    for x in range(0, 15):
        date.append(time.strftime("%Y-%m-%d"))
        time = time + datetime.timedelta(1)
        base.append(predict[-1])
        X.append(j)
        j += 1
        trainX = []
        trainY = []
        for x in range(0, len(base) - 10):
            tempX = base[x:x + 10]
            tempY = base[x + 10]
            sum = 0
            for every in tempX:
                sum += every
            for k in range(0, len(tempX)):
                tempX[k] = float(tempX[k]) / float(sum)
            tempY = float(tempY) / float(sum)
            trainX.append(tempX)
            trainY.append(tempY)
        testX = base[-10:]
        sum = 0
        for every in testX:
            sum += every
        for i in range(0, len(testX)):
            testX[i] = float(testX[i]) / float(sum)
        price = get_predict(trainX, trainY, testX)*sum
        var = np.var(np.array(base)) / len(base)
        d = np.sqrt(var)
        predict.append(price)
        predict_upper.append(price + d)
        predict_low.append(price - d)

    # print actual
    # print predict
    # print X

    # hit = 0
    # total = 0
    #
    # for x in range(0, len(actual)):
    #     total += 1
    #     if actual[x] == 1 and predict[x] > 0.5:
    #         hit += 1
    #     elif actual[x] == 0 and predict[x] < 0.5:
    #         hit += 1
    # print float(hit) / float(total)

    # plt.plot(X[:-14], actual, 'g')
    # plt.plot(X, predict, 'r')
    # plt.plot(X, predict_upper, 'b')
    # plt.plot(X, predict_low, 'y')
    # # plt.ylim(-4, 4)
    # # plt.xlim(200, 300)
    # plt.show()
    data = []
    for x in range(0, len(date)):
        if date[x] <= now:
            data.append([id, date[x], predict[x], predict_upper[x], predict_low[x], 0])
        else:
            data.append([id, date[x], predict[x], predict_upper[x], predict_low[x], 1])
        # cursor.execute('update `forecast` set `price_middle` = %s, `price_high` = %s, `price_low` = %s '
        # ' where `stockid` = "%s" and `date` = "%s"' % (predict[x], predict_upper[x], predict_low[x], id, date[x]))
    insert_cmd = 'insert into `bp` (`symbol`, `dataTime`, `price_middle`, `price_high`, `price_low`, `unstable`) ' \
                 ' VALUES (%s, %s, %s, %s, %s,%s)'

    cursor.executemany(insert_cmd, tuple(data))
    db.commit()
    db.close()