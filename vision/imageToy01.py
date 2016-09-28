import os
import requests
from flask import Flask, flash, request, redirect, url_for, send_from_directory, render_template
from flask_restful import Resource, Api
from flask_uploads import UploadSet, configure_uploads, patch_request_class, uploads_mod
from werkzeug.utils import secure_filename
from werkzeug.wsgi import SharedDataMiddleware

UPLOAD_FOLDER = '../server/storage/uploads'
VISION_WORK_FOLDER = '../server/storage/xw'
ALLOWED_EXTENSIONS = set(['gif', 'jpg', 'jpeg', 'png', 'svg', 'tif', 'tiff'])

app = Flask(__name__)
app.secret_key = 'secret'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['VISION_WORK_FOLDER'] = VISION_WORK_FOLDER
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024
patch_request_class(app, 32 * 1024 * 1024)

# def allowed_file(filename):
#   return '.' in filename and filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS

# @app.route('/', methods=['GET', 'POST'])
# def upload_file():
#   if request.method == 'POST':
#     # check if the post request has the file part
#     if 'file' not in request.files:
#       flash('No file part')
#       return redirect(request.url)
#     file = request.files['file']
#     # if user does not select file, browser submits empty part with no filename
#     if file.filename == '':
#       flash('No selected file')
#       return redirect(request.url)
#     if file and allowed_file(file.filename):
#       filename = secure_filename(file.filename)
#       file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
#       return redirect(url_for('view_file_endpoint',
#                   filename=filename))
#   return '''
#   <!doctype html>
#   <title>Upload new File</title>
#   <h1>Upload new File</h1>
#   <form action="" method=post enctype=multipart/form-data>
#     <p><input type=file name=file>
#      <input type=submit value=Upload>
#   </form>
#   '''

#@app.route('/uploads/<filename>')
#def uploaded_file(filename):
#  return send_from_directory(app.config['UPLOAD_FOLDER'], filename)

app.add_url_rule('/uploads/<filename>', 'view_file_endpoint', build_only=True)
app.wsgi_app = SharedDataMiddleware(app.wsgi_app, {
  '/uploads':  app.config['UPLOAD_FOLDER']
})


photos = UploadSet('xw', ALLOWED_EXTENSIONS)  
app.config['UPLOADED_XW_DEST'] = VISION_WORK_FOLDER
app.config['UPLOADED_XW_URL'] = 'http://localhost:3000/api/Containers/xw/'
app.config['UPLOADED_XW_ALLOW'] = ALLOWED_EXTENSIONS

@app.route('/xw', methods=['GET', 'POST'])
def upload():
    if request.method == 'POST' and 'photo' in request.files:
      file = request.files['photo']
      # if user does not select file, browser submits an empty part without filename
      if file and file.filename != '' and photos.file_allowed(file, file.filename):
        filename = secure_filename(file.filename)
        print(filename)
        flash(filename)
        filename = photos.save(request.files['photo'], folder='userid', name=filename)
        print(filename)
        flash(filename)

        # rec = Photo(filename=filename, user='userid')
        # rec.store()
        flash("Photo saved.")
        tokens = filename.split('/')
        return redirect(url_for('show', folder=tokens[0], id=tokens[1]))
        # return redirect(url_for('uploaded_file', filename=filename))
      else:
        if file.filename == '':
          flash('No file selected')
          return redirect(url_for('upload'))
        else:
          flash('File content not allowed')
          return redirect(url_for('upload'))
    else:
      if request.method == 'GET':
        return render_template('./upload.html')
      else:
        print request.method
        # response.status_code = 500
        abort(404)

@app.route('/xw/<folder>/<id>')
def show(folder, id):
    # photo = Photo.load(id)
    # if photo is None:
    #     abort(404)
    # url = photos.url(photo.id)
    return send_from_directory(app.config['VISION_WORK_FOLDER'] + '/' + folder, id)
    # return render_template('show.html', url=url, photo=id)

configure_uploads(app, (photos))

api = Api(app)

todos = {}

class TodoSimple(Resource):
  def get(self, todo_id):
    return {todo_id: todos[todo_id]}

  def put(self, todo_id):
    todos[todo_id] = request.form['data']
    return {todo_id: todos[todo_id]}

api.add_resource(TodoSimple, '/todos/<string:todo_id>')

if __name__ == '__main__':
  app.run(debug=True)

