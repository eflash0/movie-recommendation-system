FROM python:3.9-slim

WORKDIR /recommendation

COPY . /recommendation/

RUN pip install --no-cache-dir -r requirements-txt

EXPOSE 5000

ENV NAME env

CMD [ "python" , "recommendation/app/app.py"]