from flask import Flask, jsonify, request
from flask_restful import Api, Resource, reqparse
import tensorflow as tf
#from keras.preprocessing.image import ImageDataGenerator, array_to_img, img_to_array, load_img
from tensorflow.keras.models import load_model
import pickle
import numpy as np
import json

app = Flask(__name__)
api = Api(app)

# Create parser for the payload data
#parser = reqparse.RequestParser()
#parser.add_argument('data')

# Define how the api will respond to the post requests
class AnimalDetector(Resource):
    def post(self):
        try:
            print("entra")
            json_data = request.get_json(force=True)
            print ("lee")
            #print(json_data)
            x = np.array(json_data["data"])
            
            x=x/255
            x = x.reshape((1,) + x.shape)
            prediction= model.predict(x)
            acertado=False
            for a in prediction[0]:
                if(a>0.5):
                    acertado=True
            pred=[]
            if(acertado):
                pred = np.argmax(prediction,axis=1)
            else:
                pred.append(-1)
            
            print(pred[0])
            mensaje= '{"value":'+ str(pred[0])+'}'
            print(mensaje)
            return json.loads(mensaje)
        except Exception as e:
            print("ERROR : "+str(e))
        

api.add_resource(AnimalDetector, '/predict')

if __name__ == '__main__':
    # Load model
    # with open('bird_classification_convolutional_model5.h5', 'rb') as f:
    #    model = pickle.load(f)
    model = load_model('animal_classification_VGG15.h5')
    app.run(host="0.0.0.0",port=8081, debug=True)



