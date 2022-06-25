import pandas as pd
from flask import Flask,render_template,request,session,redirect,jsonify
app = Flask(_name_)
from DBConnection import Db
p=pd.read_csv("/static/student\\studentdetails.csv")
# print(p)
# print("Column headings:")
print(len(p))
for i in range(len(p)):
    print(p["s_name"][i])