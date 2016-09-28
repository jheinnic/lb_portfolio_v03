import os
import requests
import uuid
from flask import Flask, flash, request, redirect, url_for, send_from_directory, render_template
from flask_restful import Resource, Api
from flask_uploads import UploadSet, configure_uploads, patch_request_class, uploads_mod
import xwvision02

UPLOADED_TICKETS_DEST = '../server/storage/tickets'
UPLOADED_TICKETS_ALLOW = set(['gif', 'jpg', 'jpeg', 'png', 'svg', 'tif', 'tiff'])

app = Flask(__name__)
app.secret_key = 'aKeytoUse'
app.config['UPLOADED_TICKETS_DEST'] = UPLOADED_TICKETS_DEST
app.config['UPLOADED_TICKETS_URL'] = 'http://localhost:3000/api/Containers/tickets/'
app.config['UPLOADED_TICKETS_ALLOW'] = UPLOADED_TICKETS_ALLOW
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024
patch_request_class(app, 32 * 1024 * 1024)

tickets = UploadSet('tickets', UPLOADED_TICKETS_ALLOW)  

@app.route('/tickets', methods=['GET', 'POST'])
def upload():
  if request.method == 'POST' and 'photo' in request.files:
    file = request.files['photo']
    # if user does not select file, browser submits an empty part without filename
    if file.filename == '':
      flash('No file selected')
      return redirect(url_for('upload'))
    if tickets.file_allowed(file, file.filename):
      # baseName = secure_filename(file.filename)
      folderName = str(uuid.uuid1())
      fileName = tickets.save(request.files['photo'], folder=folderName, name='ticket.')
      print fileName

      # rec = Photo(filename=filename, user='userid')
      # rec.store()
      flash("Photo saved.")
      return redirect(url_for('show_upload', uuid=folderName, file=fileName.split('/')[1]))
    else:
      flash('File content not allowed')
      return redirect(url_for('upload'))
  else:
    if request.method == 'GET':
      return render_template('upload.html')
  abort(404)

@app.route('/tickets/<uuid>/<file>')
def show_upload(uuid, file):
  return send_from_directory(app.config['VISION_WORK_FOLDER'] + '/' + uuid, file)

configure_uploads(app, (tickets))

api = Api(app)

if __name__ == '__main__':
  app.run(debug=True)

