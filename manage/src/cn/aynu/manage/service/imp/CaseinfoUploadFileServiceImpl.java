package cn.aynu.manage.service.imp;

import javax.annotation.Resource;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.aynu.manage.dao.imp.CaseinfoUploadFileDaoImpl;
import cn.aynu.manage.service.CaseinfoUploadFileService;
import cn.aynu.manage.vo.UploadFile;
@Service("caseinfoUploadFileService")
public class CaseinfoUploadFileServiceImpl implements CaseinfoUploadFileService {
	@Resource
	private CaseinfoUploadFileDaoImpl caseinfoUploadFileDao;

	public void saveEntity(UploadFile c) {
		caseinfoUploadFileDao.saveEntity(c);
	}
	
	@Resource
	private CaseinfoUploadFileDaoImpl uploadDao; 
	
	/**
	 * �����������ϴ��ļ�������
	 */
	public List<UploadFile> findExistingFilesNameByCaseinfoId(Integer caseinfoId) {
		String hql = "from CaseinfoUploadFile where caseinfo.id = ?";
		return uploadDao.findEntityByHQL(hql, caseinfoId);
	}
	
	/**
	 * �����ļ����õ�Ŀ���ļ�
	 */
	public UploadFile  getDestFileByFileId(Integer id) {
		String hql = "from CaseinfoUploadFile where id = ?";
		return (UploadFile) uploadDao.uniqueResult(hql, id);
	}

	/**
	 * �����ļ�����ɾ�����ݿ��е��ļ���¼
	 * @param destFileName
	 */
	public void deleteFileInfoByFileId(Integer id) {
		uploadDao.deleteEntity(getDestFileByFileId(id));
	}
}
