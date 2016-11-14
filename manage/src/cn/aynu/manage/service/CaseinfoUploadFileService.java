package cn.aynu.manage.service;

import cn.aynu.manage.vo.UploadFile;

import java.util.List;

import cn.aynu.manage.vo.UploadFile;

public interface CaseinfoUploadFileService {
	/**
	 * �����������ϴ�de�ļ�
	 * @return
	 */
	public List<UploadFile> findExistingFilesNameByCaseinfoId(Integer caseinfoId);
	
	/**
	 * �����ļ�id�õ�Ŀ���ļ�
	 * @param destFileName
	 * @return
	 */
	public UploadFile getDestFileByFileId(Integer id);

	void saveEntity(UploadFile c);

	/**
	 * �����ļ�idɾ�����ݿ��е��ļ���¼
	 * @param destFileName
	 */
	public void deleteFileInfoByFileId(Integer id);
	
}
