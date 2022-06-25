import PyPDF2,yake
from DBConnection import Db
gl_list=[".","-","(",")","_","'",":",",","[","]","@","%","?","/","!","“",":",";","``","a","b","c","d","e","f","g","h",
         "i","j","k","l","m","n","o","p","q","r","s","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","10","0","’",'"',"''"
         "...................","i.e","3.4.3","3.3","``","will","only","pty","ttd.","of","no","reg","fulfillmentof","award","degree","of","and","dist","hereby","declare",
         "sub-","mitted","Insti-","Date","the","place","camscanner","this","certify","carried","year","Guide","held","Mr.","de-","gav","key","accuracy","did","new",
         "due","dew","not","tea","note","come-on","concerning","concerninger","concerningest","consequently","considering","eg","eight",
         "your","yours","yourself","yourselves","you","yond","yonder","yon","ye","yet","zillion","umpteen","usually","username","uponed","upons","uponing","upon","ups", "upping", "upped", "up", "unto", "until", "unless", "unlike", "unliker", "unlikest", "under",
           "i","ii","iii","iv"                                                                                          "underneath",
         "use", "used", "usedest", "rath", "rather", "rathest", "rathe", "re", "relate", "related", "relatively",
         "regarding", "really", "res", "respecting", "respectively", "quite","que","qua","neither","neaths","neath","nethe","nethermost","necessary","necessariest","necessarier","never","nevertheless","nigh","nighest","nigher","nine","noone","nobody",
"ten","tills","till","tilled","tilling","to","towards","toward","towardest","towarder","together","too","thy","thyself","thus","than","that",
"those","thou","though","thous","thouses","thoroughest","thorougher","thorough","thoroughly","thru","thruer","thruest","thro","through","throughout","throughest",
"througher","thine","this","thises","they","thee","the","{","}","[","]",
"nobodies","nowhere","nowheres","no","noes","or","nos","no-one","none","not","notwithstanding","nothings","nothing","nathless","natheless"," ",'"']
def abcde(path,pid):
    # creating a pdf reader object
    pdfFileObj=open(path,'rb')
    pdfReader = PyPDF2.PdfFileReader(pdfFileObj)
    # printing number of pages in pdf file
    print(pdfReader.numPages)
    # creating a page object
    gg = ""
    for i in range(0, pdfReader.numPages):
        print(i)
        pageObj = pdfReader.getPage(i)
        gg = gg + " " + pageObj.extractText()
        # extracting text from page
        text = pageObj.extractText()
        print(text)
        # language = "en"
        # max_ngram_size = 3
        # deduplication_threshold = 0.9
        # numOfKeywords = 10
        # custom_kw_extractor = yake.KeywordExtractor(lan=language, n=max_ngram_size, top=numOfKeywords,
        #                                             features=None)
        # keywords = custom_kw_extractor.extract_keywords(gg)

        pdfFileObj.close()
        a = gg.replace(",", "").replace(".", "").replace("0", "").replace("1", "").replace("2", "").replace("3",
                                                                                                            "").replace(
            "4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("0", "").replace(
            "!",
            "").replace(
            "@", "").replace('-', "")

        kw_extractor = yake.KeywordExtractor()
        language = "en"

        max_ngram_size = 3
        deduplication_threshold = 0.9
        numOfKeywords = 200
        custom_kw_extractor = yake.KeywordExtractor(lan=language, n=max_ngram_size, top=numOfKeywords,
                                                    features=None)
        keywords = custom_kw_extractor.extract_keywords(a)
        for kw in keywords:
            print(kw[0])
            print(kw[1])
            pop=str(kw[0]).lower()
            if pop not in gl_list:
                try:
                    qry="INSERT INTO `ngram`(`pid`,`word`,`score`)VALUES('"+pid+"','"+pop+"','"+str(kw[1])+"')"
                    db=Db()
                    db.insert(qry)
                except :
                    pass

# abcde('C:\\Users\\Angel Rose\\PycharmProjects\\project_management\\static\\journal\\20220517-103759.pdf',1)
#         'C:\\Users\\User\\PycharmProjects\\project_management12\\static\\report\\20220509-021543.pdf'