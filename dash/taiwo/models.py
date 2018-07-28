from django.db import models

# Create your models here.
from datetime import datetime, timedelta
import string as str
from random import choice


def generate_id():
        n = 10
        random = str.ascii_uppercase + str.ascii_lowercase + str.digits
        return ''.join(choice(random) for _ in range(n))

def user_directory_path(instance, filename):
    # file will be uploaded to MEDIA_ROOT/user_<id>/<filename>
    return 'user_{0}/{1}'.format(instance.user.id, filename)

class Phone(models.Model):
    user = models.ForeignKey('auth.User', on_delete=models.CASCADE)
    date_created = models.DateTimeField(auto_now_add=True)
    # date_updated = models.DateTimeField(auto_now=True)
    expire_on = datetime.now() + timedelta(31)
    phone_description = models.CharField(max_length=100)
    slug = models.SlugField(unique=True, max_length=10, blank=True)
    video = models.FileField(upload_to=user_directory_path)

    def __str__(self):
        return "{0}, {1}".format(self.user, self.phone_description)
