    'use strict';

    module.exports = function (CrosswordProject) {

      // TODO: Use a user-associated container Id.
      CrosswordProject.upload = function (ctx, options, cb) {
        if (!options) options = {};
        ctx.req.params.container = 'common';

        var Container = CrosswordProject.app.models.Container;
        var FiveXTicket = CrosswordProject.app.models.FiveXTicket;

        Container.upload(
          ctx.req, ctx.result, options,
          function (err, fileObj) {
            if (err) {
              console.error(err);
              cb(err);
            } else {
              console.log(fileObj);
              var fileInfo = fileObj.files.file[0];
              FiveXTicket.create({
                ticketId: '11111111-101',
                gridContent: fileInfo.name + fileInfo.container,
                yourLetters: '__________________',
                finalPayout: -1,
                fastValue: 0,
                multiValue: 0
              }, cb);
            }
          }
        );
      };

      CrosswordProject.remoteMethod(
        'import',
        {
          description: 'Uploads a ticket source file and creates a metadata record for it.',
          accepts: [
            {arg: 'ctx', type: 'object', http: {source: 'context'}},
            {arg: 'options', type: 'object', http: {source: 'query'}}
          ],
          returns: {
            arg: 'fileObject', type: 'object', root: true
          },
          http: {verb: 'post'}
        }
      );

    };
